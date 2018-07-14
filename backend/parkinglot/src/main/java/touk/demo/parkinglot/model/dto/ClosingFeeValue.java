package touk.demo.parkinglot.model.dto;

import touk.demo.parkinglot.model.response.ServiceResponse;

public class ClosingFeeValue implements ServiceResponse {

  private int id;
  private double fee;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }
}
