package touk.demo.parkinglot.calculator;

import touk.demo.parkinglot.model.response.ServiceResponse;

public interface Calculator<A, R extends ServiceResponse> {

  R getFeeValue(A argument);
}