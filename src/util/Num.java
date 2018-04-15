package util;

public enum  Num {
    ShowPostsPerPageNum(10),ShowFollowpostsPerPageNum(5),
    ShowManageUserPerPageNum(1),ShowPageListSize(20);
    private int value;
    Num(int v)
    {
        value=v;
    }

    public int getValue() {
        return value;
    }
}
