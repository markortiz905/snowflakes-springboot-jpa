package com.snowflakes.demo;

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mark anthony ortiz
 */
@ToString
@Entity
@Table(schema = "PUBLIC", catalog = "INLIFE_MARK", name = "test_object")
public class TestObject {

    @Id
    @GeneratedValue(generator = "MILLIS")
    @GenericGenerator(
            name = "MILLIS",
            strategy = "com.snowflakes.demo.CustomIdentifierGenerator"
    )
    public Long id;

    public String name;
}
