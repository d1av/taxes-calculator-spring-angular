package com.taxes.calculator.application;

public interface UnitUseCase<T> {

    public abstract void execute(T onIn);
    
}
