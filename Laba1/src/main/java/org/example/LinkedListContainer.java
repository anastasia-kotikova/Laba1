package org.example;

/**
 * Пользовательская реализация контейнера на основе односвязного списка.
 * Данная реализация предоставляет базовые операции с контейнером без использования
 * встроенных коллекций Java.
 *
 * <p>Односвязный список состоит из узлов, где каждый узел содержит данные
 * и ссылку на следующий узел. Реализация поддерживает ссылки на начало (head)
 * и конец (tail) списка для эффективного добавления элементов в конец.</p>
 *
 * @param <T> тип элементов в этом контейнере
 * @author Анастасия Котикова
 */
public class LinkedListContainer<T> implements LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Создает пустой контейнер.
     *
     * <p>Инициализирует начальные значения:
     * <ul>
     *   <li>head = null (начало списка)</li>
     *   <li>tail = null (конец списка)</li>
     *   <li>size = 0 (размер списка)</li>
     * </ul>
     * </p>
     */
    public LinkedListContainer() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация добавляет элемент в конец списка.</p>
     *
     * @throws IllegalArgumentException если указанный элемент равен null
     */
    @Override
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация выполняет последовательный поиск от начала списка
     * до указанного индекса.</p>
     *
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые пределы
     */
    @Override
    public T get(int index) {
        checkIndex(index);

        Node<T> current = getNode(index);
        return current.data;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация обрабатывает три случая:
     * <ul>
     *   <li>Удаление из начала списка.</li>
     *   <li>Удаление из конца списка. </li>
     *   <li>Удаление из середины списка.</li>
     * </ul>
     * </p>
     *
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые пределы
     */
    @Override
    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<T> previous = getNode(index - 1);
        T removedData = previous.next.data;
        previous.next = previous.next.next;

        if (previous.next == null) {
            tail = previous;
        }

        size--;
        return removedData;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация выполняет поиск первого вхождения элемента и удаляет его.</p>
     *
     * @return true если элемент был найден и удален, false если элемент не найден
     */
    @Override
    public boolean remove(T element) {
        if (element == null || head == null) {
            return false;
        }

        if (head.data.equals(element)) {
            removeFirst();
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;

            if (current.next == null) {
                tail = current;
            }

            size--;
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return количество элементов в контейнере
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     *
     * @return true если контейнер пуст, false в противном случае
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация очищает все ссылки, позволяя сборщику мусора
     * освободить память, занятую узлами списка.</p>
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация использует метод indexOf для проверки наличия элемента.</p>
     *
     * @return true если элемент содержится в контейнере, false в противном случае
     */
    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Реализация выполняет линейный поиск от начала списка.</p>
     *
     * @return индекс первого вхождения элемента или -1 если элемент не найден
     */
    @Override
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }

        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Возвращает строковое представление этого контейнера.
     * Строковое представление состоит из списка элементов контейнера
     * в порядке их хранения, заключенных в квадратные скобки ("[]").
     *
     * @return строковое представление этого контейнера
     */
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;

        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Приватные вспомогательные методы

    /**
     * Удаляет и возвращает первый элемент из этого контейнера.
     *
     * <p>Этот метод используется при удалении элемента по индексу 0
     * или при удалении элемента по значению из начала списка.</p>
     *
     * @return первый элемент из этого контейнера
     */
    private T removeFirst() {
        T removedData = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return removedData;
    }

    /**
     * Возвращает узел по указанной позиции в этом контейнере.
     *
     * <p>Метод выполняет последовательный проход от начала списка
     * до указанного индекса. Используется внутренними методами
     * для доступа к узлам списка.</p>
     *
     * @param index индекс возвращаемого узла
     * @return узел по указанной позиции
     */
    private Node<T> getNode(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Проверяет корректность указанного индекса.
     *
     * <p>Метод проверяет, что индекс находится в диапазоне
     * от 0 (включительно) до size (исключительно).</p>
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс неверный
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", index, size));
        }
    }
}
