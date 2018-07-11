package touk.demo.parkinglot.converter;

@FunctionalInterface
public interface BaseConverter<F, T> {

  T convert(F from);
}
