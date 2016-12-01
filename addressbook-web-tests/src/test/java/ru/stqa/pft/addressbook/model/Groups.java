package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chesnokova.sa on 20.11.2016.
 */
public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData>  delegate;

    public Groups (Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups () {
        this.delegate = new HashSet<GroupData>();
    }

    //строим множество объектов из коллекции, создаем копию
    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    //добавление группы
    public Groups withAdded (GroupData group)  {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    //удаление группы
    public Groups withOut (GroupData group)  {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
