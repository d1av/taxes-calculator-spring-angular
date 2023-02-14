package com.taxes.calculator.application;

public interface UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}
