package hu2;

public class People {
  private Integer id;
  private String name;
  public People(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
  public Integer getId() {
    return this.id;
  }
  public String getName() {
    return this.name;
  }
  @Override
  public boolean equals(Object obj) {
    return this.id == ((People)obj).id;
  }
}
