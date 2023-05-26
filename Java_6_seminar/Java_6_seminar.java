// Подумать над структурой класса Ноутбук для магазина техники — выделить поля и методы. Реализовать в Java.

// Создать множество ноутбуков.

// Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:

// “Введите цифру, соответствующую необходимому критерию:

// 1 - ОЗУ

// 2 - Объём ЖД

// 3 - Операционная система

// 4 - Цвет …

// Далее нужно запросить минимальные и максимальные значения для указанных критериев — сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.*;

public class LaptopFilter {
  
  public static void main(String[] args) {
    List<Laptop> laptops = Arrays.asList(
      new Laptop("Dell", "Windows", 8, 512, "Black"),
      new Laptop("Lenovo", "Windows", 16, 256, "Silver"),
      new Laptop("Apple", "MacOS", 8, 256, "Silver"),
      new Laptop("HP", "Windows", 16, 512, "Blue")
    );
    
    Map<Integer, String> criteriaMap = new HashMap<>();
    criteriaMap.put(1, "RAM");
    criteriaMap.put(2, "HDD");
    criteriaMap.put(3, "OS");
    criteriaMap.put(4, "Color");
    
    Map<String, Integer[]> filters = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    
    for (Integer criterion : criteriaMap.keySet()) {
      System.out.println("Enter minimum " + criteriaMap.get(criterion) + ":");
      int min = scanner.nextInt();
      System.out.println("Enter maximum " + criteriaMap.get(criterion) + ":");
      int max = scanner.nextInt();
      filters.put(criteriaMap.get(criterion), new Integer[]{min, max});
    }
    
    List<Laptop> filteredLaptops = filterLaptops(laptops, filters);
    for (Laptop laptop : filteredLaptops) {
      System.out.println(laptop.toString());
    }
  }
  
  private static List<Laptop> filterLaptops(List<Laptop> laptops, Map<String, Integer[]> filters) {
    List<Laptop> filteredLaptops = new ArrayList<>();
    for (Laptop laptop : laptops) {
      if (isLaptopMatchingFilters(laptop, filters)) {
        filteredLaptops.add(laptop);
      }
    }
    return filteredLaptops;
  }
  
  private static boolean isLaptopMatchingFilters(Laptop laptop, Map<String, Integer[]> filters) {
    for (String criteria : filters.keySet()) {
      Integer[] range = filters.get(criteria);
      if (criteria.equals("RAM") && (laptop.getRam() < range[0] || laptop.getRam() > range[1])) {
        return false;
      }
      if (criteria.equals("HDD") && (laptop.getHdd() < range[0] || laptop.getHdd() > range[1])) {
        return false;
      }
      if (criteria.equals("OS") && !laptop.getOs().equals(range[0].toString())) {
        return false;
      }
      if (criteria.equals("Color") && !laptop.getColor().equals(range[0].toString())) {
        return false;
      }
    }
    return true;
  }
  
}

class Laptop {
  private String brand;
  private String os;
  private int ram;
  private int hdd;
  private String color;
  
  public Laptop(String brand, String os, int ram, int hdd, String color) {
    this.brand = brand;
    this.os = os;
    this.ram = ram;
    this.hdd = hdd;
    this.color = color;
  }
  
  public String getBrand() {
    return brand;
  }
  
  public void setBrand(String brand) {
    this.brand = brand;
  }
  
  public String getOs() {
    return os;
  }
  
  public void setOs(String os) {
    this.os = os;
  }
  
  public int getRam() {
    return ram;
  }
  
  public void setRam(int ram) {
    this.ram = ram;
  }
  
  public int getHdd() {
    return hdd;
  }
  
  public void setHdd(int hdd) {
    this.hdd = hdd;
  }
  
  public String getColor() {
    return color;
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public String toString() {
    return brand + " " + os + " " + ram + "GB RAM " + hdd + "GB HDD " + color;
  }
}