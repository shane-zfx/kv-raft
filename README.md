# KV-Raft

> 实现raft的kv应用

## 集群节点

1. 节点角色

   > 节点的通讯状态
   >
   > - 如果 `commitIndex` > `lastApplied` : 增加已被应用到状态机的最新日志条目的索引
   > - 如果 RPC 的 request 或者 response 包含的 `T > currentTerm`，设置 `currentTerm = T`，角色切换为从节点

    - 主节点

        - 选举结束后，空闲期在集群内广播空的日志条目（心跳），防止选举超时
        - 如果接收到来自客户端的命令之后，在本地添加一条日志，并在日志条目应用到状态机后响应客户端
        - 如果从节点的最新日志条目索引号大于主节点的下一条将发送的日志索引号，发送携带从 [`nextIndex`](#nextIndex)
          开始的日志条目的 `AppendEntries RPC`

            - 如果发送成功，更新从节点的 `nextIndex` 和 `matchIndex`
            - 如果因为日志不一致而失败，则 nextIndex 递减并重试
        - 假设存在 N 满足 `N > commitIndex` ，使得大多数的 `matchIndex[i] ≥ N ` 以及 `log[N].term == currentTerm`
          成立，则令 `commitIndex = N`

    - 候选节点

        - 在晋升为候选之后，立即开启选举

            - 增加当前 Term （任期）
            - 给自己投票
            - 重置选举计时器
            - 广播 `Request RPC`

        - 如果接收到过半投票之后，晋升为主节点
        - 如果接收到新的主节点的 `AppendEntries RPC` ，退化为从节点
        - 如果选举超时，开启新的一轮选举

    - 从节点

        - 响应来自候选节点或者主节点的 RPC 请求
        - 如果直到选举超时，没有收到当前主节点的心跳或者没有给候选节点投票，则自己晋升为候选节点

## 节点选举

> 当集群启动时，所有节点初始化为 `follower` 状态，加载计时器，期间等候可能来自 `candidate` 或 `leader` 的 `rpc` 。

1. 选举计时器
    
    - 选举计时器：从节点等待晋升候选节点的时间（随机 150ms ~ 300ms）
    
        > 某节点晋升为候选节点，开启新一轮任期计数，首先自投一票，然后在集群内广播请求投票消息 （广播过程总，也有其他节点成为候选几点怎么办？直接丢弃信息？）
        >
        > 如果接收到投票请求的节点，还没投过票，则将票投给该候选节点。这些投票节点重置自身的选举计时器
        >
        > 候选节点收到过半投票，晋升为`leader`，广播信息，此时 `leader` 会按照心跳计时器的时间间隔，定时广播心跳，收到心跳的从节点不断刷新自己的选举计时器，直到发生 re-elect


> 变量通用说明
>
> persistent state on all servers
>
> - <span id = "currentTerm">currentTerm</span>：集群已知的最新任期号（单调递增）
> - <span id = "votedFor">votedFor</span>：当前任期收到投票的候选节点 id
>
> volatile state on all servers
> volatile state on leaders
> - <span id = "nextIndex">nextIndex[]</span>：对每个节点来说，将发送到该节点的下一条日志条目索引号（初始化为主节点最后的日志条目索引号加
    1）
> - <span id = "matchIndex">matchIndex[]</span>：对每个节点来说，已知已经复制到该节点的最新日志条目索引号（初始化为 0
    ，单调递增）
      
    