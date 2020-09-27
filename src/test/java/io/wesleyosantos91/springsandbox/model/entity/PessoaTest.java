package io.wesleyosantos91.springsandbox.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import io.wesleyosantos91.springsandbox.utils.GetterAndSetterTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.io.Serializable;
import java.util.Set;

class EntityTest {

    Set<Class<? extends Serializable>> allClasses;
    GetterAndSetterTester tester;

    @BeforeEach
    public void setUp() {
        tester = new GetterAndSetterTester();
        Reflections reflections = new Reflections("io.wesleyosantos91.springsandbox.model.entity");
        allClasses = reflections.getSubTypesOf(Serializable.class);
    }

    @Test
    @DisplayName("Deve Testar todas as entidades")
    void testarTodasEntidades() {
        for (Class<? extends Object> clazz : allClasses)
            tester.testClass(clazz);
    }
}