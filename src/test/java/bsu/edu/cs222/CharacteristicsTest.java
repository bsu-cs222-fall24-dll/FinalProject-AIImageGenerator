package bsu.edu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacteristicsTest {
    Characteristics characteristics;

    @BeforeEach
    public void setUp() {
        characteristics = new Characteristics("male", "Caucasian", "25", "brown", "blue", "athletic", "black and white", "enemy", "FPS","Human","white");
    }

    @Test
    public void testInitialization() {
        assertNotNull(characteristics);
    }

    @Test
    public void testGetSex() {
        assertEquals("male", characteristics.getSex());
    }
    @Test
    public void testGetRace() {
        assertEquals("Caucasian", characteristics.getRace());
    }
    @Test
    public void testGetAge() {
        assertEquals("25", characteristics.getAge());
    }
    @Test
    public void testGetHairColor() {
        assertEquals("brown", characteristics.getHairColor());
    }
    @Test
    public void testGetEyeColor() {
        assertEquals("blue", characteristics.getEyeColor());
    }
    @Test
    public void testGetBodyStyle() {
        assertEquals("athletic", characteristics.getBodyStyle());
    }
    @Test
    public void testGetArtStyle() {
        assertEquals("black and white", characteristics.getArtStyle());
    }
    @Test
    public void testGetCharacterType() {
        assertEquals("enemy", characteristics.getCharacterType());
    }
    @Test
    public void testGetGameType() {
        assertEquals("FPS", characteristics.getGameType());
    }

    @Test
    public void testSetSex() {
        characteristics.setSex("female");
        assertEquals("female", characteristics.getSex());
    }
    @Test
    public void testSetRace() {
        characteristics.setRace("asian");
        assertEquals("asian", characteristics.getRace());
    }
    @Test
    public void testSetAge() {
        characteristics.setAge("22");
        assertEquals("22", characteristics.getAge());
    }
    @Test
    public void testSetHairColor() {
        characteristics.setHairColor("red");
        assertEquals("red", characteristics.getHairColor());
    }
    @Test
    public void testSetEyeColor() {
        characteristics.setEyeColor("green");
        assertEquals("green", characteristics.getEyeColor());
    }
    @Test
    public void testSetBodyStyle() {
        characteristics.setBodyStyle("huge");
        assertEquals("huge", characteristics.getBodyStyle());
    }
    @Test
    public void testSetArtStyle() {
        characteristics.setArtStyle("18th century");
        assertEquals("18th century", characteristics.getArtStyle());
    }
    @Test
    public void testSetCharacterType() {
        characteristics.setCharacterType("antagonist");
        assertEquals("antagonist", characteristics.getCharacterType());
    }
    @Test
    public void testSetGameType() {
        characteristics.setGameType("PVE");
        assertEquals("PVE", characteristics.getGameType());
    }
}
