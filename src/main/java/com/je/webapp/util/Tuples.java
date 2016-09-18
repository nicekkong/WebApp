package com.je.webapp.util;

public class Tuples {
    
    private Tuples() {}
    
    public static <T1, T2> Tuple2<T1, T2> tuple(T1 v1, T2 v2) {
        return new Tuple2<T1, T2>(v1, v2);
    }
    
    public static <T1, T2, T3> Tuple3<T1, T2, T3> tuple(T1 v1, T2 v2, T3 v3) {
        return new Tuple3<T1, T2, T3>(v1, v2, v3);
    }
    
    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tuple(T1 v1, T2 v2, T3 v3, T4 v4) {
        return new Tuple4<T1, T2, T3, T4>(v1, v2, v3, v4);
    }
    
    public static class Tuple2<T1, T2> {
        public final T1 v1;
        public final T2 v2;
        
        private Tuple2(T1 v1, T2 v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
    
    public static class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
        public final T3 v3;
        
        private Tuple3(T1 v1, T2 v2, T3 v3) {
            super(v1, v2);
            this.v3 = v3;
        }
    }
    
    public static class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {
        public final T4 v4;
        
        private Tuple4(T1 v1, T2 v2, T3 v3, T4 v4) {
            super(v1, v2, v3);
            this.v4 = v4;
        }
    }
}
