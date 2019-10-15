package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;

public class Items extends ForwardingSet<ItemData> {
  private Set<ItemData> delegate;

  public Items(Items items) {
    this.delegate = new HashSet<ItemData>(items.delegate);
  }

  public Items() {
    this.delegate = new HashSet<ItemData>();
  }

  @Override
  protected Set<ItemData> delegate() {
    return delegate;
  }

  public Items withAdded (ItemData item) {
    Items items = new Items(this);
    items.add(item);
    return items;
  }

  public Items without (ItemData item) {
    Items items = new Items(this);
    items.remove(item);
    return items;
  }
}