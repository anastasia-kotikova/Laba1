import org.example.LinkedListContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты для класса LinkedListContainer.
 *
 * <p>Этот тестовый класс проверяет корректность работы всех методов контейнера
 * на основе односвязного списка, включая граничные случаи и исключительные ситуации.</p>
 *
 * @author Анастасия Котикова
 * @see LinkedListContainer
 */
class LinkedListContainerTest {
    /**
     * Тестируемый контейнер. Пересоздается перед каждым тестом.
     */
    private LinkedListContainer<String> container;
    /**
     * Метод инициализации, выполняемый перед каждым тестом.
     * Создает новый пустой контейнер для обеспечения изоляции тестов.
     */
    @BeforeEach
    void setUp() {
        container = new LinkedListContainer<>();
    }

    /**
     * Тестирует базовые операции добавления и получения элементов.
     *
     * <p>Проверяет:
     * <ul>
     *   <li>Корректность добавления элементов</li>
     *   <li>Правильность работы счетчика размера</li>
     *   <li>Соответствие порядка элементов порядку добавления</li>
     * </ul>
     * </p>
     */
    @Test
    void testAddAndGet() {
        // Добавляем три элемента в контейнер
        container.add("Apple");
        container.add("Banana");
        container.add("Orange");
        // Проверяем размер контейнера
        assertEquals(3, container.size());
        // Проверяем корректность получения элементов по индексам
        assertEquals("Apple", container.get(0));
        assertEquals("Banana", container.get(1));
        assertEquals("Orange", container.get(2));
    }

    /**
     * Тестирует обработку попытки добавления null элемента.
     *
     * <p>Ожидается, что метод add выбросит IllegalArgumentException
     * при попытке добавления null значения.</p>
     */
    @Test
    void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> container.add(null));
    }

    /**
     * Тестирует обработку некорректных индексов при получении элементов.
     *
     * <p>Проверяет граничные случаи:
     * <ul>
     *   <li>Отрицательный индекс</li>
     *   <li>Индекс, превышающий размер контейнера</li>
     * </ul>
     * Ожидается выбрасывание IndexOutOfBoundsException в обоих случаях.
     * </p>
     */
    @Test
    void testGetWithInvalidIndex() {
        container.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    /**
     * Тестирует удаление элемента по индексу из середины списка.
     *
     * <p>Проверяет:
     * <ul>
     *   <li>Корректность возвращаемого значения (удаленный элемент)</li>
     *   <li>Обновление размера контейнера</li>
     *   <li>Корректность перелинковки оставшихся элементов</li>
     * </ul>
     * </p>
     */
    @Test
    void testRemoveByIndex() {
        container.add("A");
        container.add("B");
        container.add("C");

        String removed = container.remove(1);
        assertEquals("B", removed);
        assertEquals(2, container.size());
        assertEquals("A", container.get(0));
        assertEquals("C", container.get(1));
    }

    /**
     * Тестирует удаление первого элемента списка.
     *
     * <p>Проверяет особый случай, когда удаляемый элемент является головой списка.
     * Должна корректно обновиться ссылка head контейнера.</p>
     */
    @Test
    void testRemoveFirstElement() {
        container.add("First");
        container.add("Second");

        String removed = container.remove(0);
        assertEquals("First", removed);
        assertEquals(1, container.size());
        assertEquals("Second", container.get(0));
    }

    /**
     * Тестирует удаление последнего элемента списка.
     *
     * <p>Проверяет особый случай, когда удаляемый элемент является концом списка.
     * Должна корректно обновиться ссылка tail контейнера.</p>
     */
    @Test
    void testRemoveLastElement() {
        container.add("First");
        container.add("Last");

        String removed = container.remove(1);
        assertEquals("Last", removed);
        assertEquals(1, container.size());
        assertEquals("First", container.get(0));
    }

    /**
     * Тестирует удаление элемента по значению.
     *
     * <p>Проверяет:
     * <ul>
     *   <li>Корректность возвращаемого значения (true при успешном удалении)</li>
     *   <li>Уменьшение размера контейнера</li>
     *   <li>Фактическое отсутствие удаленного элемента в контейнере</li>
     * </ul>
     * </p>
     */
    @Test
    void testRemoveByValue() {
        container.add("Apple");
        container.add("Banana");
        container.add("Orange");

        assertTrue(container.remove("Banana"));
        assertEquals(2, container.size());
        assertFalse(container.contains("Banana"));
    }

    /**
     * Тестирует попытку удаления несуществующего элемента.
     *
     * <p>Ожидается, что метод вернет false и размер контейнера не изменится.</p>
     */
    @Test
    void testRemoveNonExistentValue() {
        container.add("Apple");

        assertFalse(container.remove("Banana"));
        assertEquals(1, container.size());
    }

    /**
     * Тестирует метод проверки пустоты контейнера.
     *
     * <p>Проверяет все возможные состояния:
     * <ul>
     *   <li>Изначально пустой контейнер</li>
     *   <li>Контейнер после добавления элементов</li>
     *   <li>Контейнер после удаления всех элементов</li>
     * </ul>
     * </p>
     */
    @Test
    void testIsEmpty() {
        assertTrue(container.isEmpty());

        container.add("Test");
        assertFalse(container.isEmpty());

        container.remove(0);
        assertTrue(container.isEmpty());
    }

    /**
     * Тестирует метод очистки контейнера.
     *
     * <p>Проверяет, что после вызова clear() контейнер становится пустым
     * и его размер сбрасывается в 0.</p>
     */
    @Test
    void testClear() {
        container.add("A");
        container.add("B");
        container.add("C");

        container.clear();

        assertTrue(container.isEmpty());
        assertEquals(0, container.size());
    }

    /**
     * Тестирует метод проверки наличия элемента в контейнере.
     *
     * <p>Проверяет как наличие существующих элементов, так и отсутствие несуществующих.</p>
     */
    @Test
    void testContains() {
        container.add("Apple");
        container.add("Banana");

        assertTrue(container.contains("Apple"));
        assertTrue(container.contains("Banana"));
        assertFalse(container.contains("Orange"));
    }

    /**
     * Тестирует поиск индекса элемента.
     *
     * <p>Проверяет:
     * <ul>
     *   <li>Поиск первого вхождения элемента</li>
     *   <li>Обработку дублирующихся элементов</li>
     *   <li>Возврат -1 для отсутствующих элементов</li>
     * </ul>
     * </p>
     */
    @Test
    void testIndexOf() {
        container.add("Apple");
        container.add("Banana");
        container.add("Apple"); // duplicate

        assertEquals(0, container.indexOf("Apple"));
        assertEquals(1, container.indexOf("Banana"));
        assertEquals(-1, container.indexOf("Orange"));
    }

    /**
     * Тестирует строковое представление контейнера.
     *
     * <p>Проверяет формат вывода для разных состояний контейнера:
     * <ul>
     *   <li>Пустой контейнер</li>
     *   <li>Контейнер с одним элементом</li>
     *   <li>Контейнер с несколькими элементами</li>
     * </ul>
     * </p>
     */
    @Test
    void testToString() {
        assertEquals("[]", container.toString());

        container.add("A");
        assertEquals("[A]", container.toString());

        container.add("B");
        container.add("C");
        assertEquals("[A, B, C]", container.toString());
    }

    /**
     * Тестирует работу с большим количеством элементов.
     *
     * <p>Проверяет производительность и корректность работы контейнера
     * при добавлении и получении 1000 элементов.</p>
     */
    @Test
    void testLargeNumberOfElements() {
        int count = 1000;

        for (int i = 0; i < count; i++) {
            container.add("Element" + i);
        }

        assertEquals(count, container.size());

        for (int i = 0; i < count; i++) {
            assertEquals("Element" + i, container.get(i));
        }
    }
}