package com.example.administrator.retrofitsample;

public class Translation {
    private int status;

    private content content;

    public content getContent(){
        return this.content;
    }

    public static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;

        public String getOut(){
            return this.out;
        }
        public void setOut(String out) {
            this.out = out;
        }
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }
}
