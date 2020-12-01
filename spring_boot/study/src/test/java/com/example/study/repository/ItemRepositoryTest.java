package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create()
    {
        Item item = new Item();
        item.setName("NoteBook");
        item.setPrice(300);
        item.setContent("MacBook");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(item);
    }

    @Test
    @Transactional
    public void read()
    {
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);

        item.ifPresent(i->{
            System.out.println(i);
        });


    }

    @Test
    public void update()
    {
    }

    @Test
    public void delete()
    {
    }



}
