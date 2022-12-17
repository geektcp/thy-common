package com.geektcp.common.mq.model.vo;

/**
 * @author Created by tanghaiyang on 2021/1/16.
 */
public enum SchemaType {
    VERTEX("vertex", false, false),
    VERTEX_MAIN("mainVertex", false, false),
    EDGE("edge", false, true),
    EDGE_DETAIL("edgeDetail", false, true),
    EDGE_SUMMARY("edgeSummary", true, true),
    EDGE_DEEP("edgeDeep", true, true);

    private String label;
    private boolean hidden;
    private boolean edge;

    SchemaType(String label, boolean hidden, boolean edge) {
        this.label = label;
        this.hidden = hidden;
        this.edge = edge;
    }

    public boolean isEdge(){
        return edge;
    }

    public boolean isVertex(){
        return !edge;
    }

    public static boolean isVertex(SchemaType type) {
        return !isEdge(type);
    }

    public static boolean isMainVertex(SchemaType type) {
        return (type == VERTEX_MAIN);
    }

    public static boolean isEdge(SchemaType type) {
        return type.edge;
    }
}
