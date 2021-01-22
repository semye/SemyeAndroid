package com.semye.base.pattern.structural.facade;

/**
 * Created by yesheng on 2018/10/20.
 */
public class ComputerFacade {

    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load();
        processor.jump();
        processor.execute();
    }

}
