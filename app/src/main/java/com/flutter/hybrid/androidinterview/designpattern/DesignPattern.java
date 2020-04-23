package com.flutter.hybrid.androidinterview.designpattern;

public class DesignPattern {

    private static class InstentInner{
        private static final DesignPattern INSTANCE = new DesignPattern();
    }

    public static DesignPattern getInstance() {
        return InstentInner.INSTANCE;
    }

    private DesignPattern() {
        int a = InstanceEnum.INSTANCE.getCount();
    }


    enum InstanceEnum{
        INSTANCE;
        public int getCount() {
            return 0;
        }
    }

}
