package org.example;

/**
 * Демонстрационный класс для использования LinkedListContainer.
 *
 * <p>Этот класс демонстрирует основные операции с контейнером на основе
 * односвязного списка: добавление, удаление, поиск и другие операции.</p>
 *
 * @author Анастасия Котикова
 * @see LinkedListContainer
 */
public class Main {
    /**
     * Основной метод демонстрации функциональности LinkedListContainer.
     *
     * <p>Метод последовательно демонстрирует:
     * <ol>
     *   <li>Создание контейнера</li>
     *   <li>Добавление элементов</li>
     *   <li>Доступ к элементам по индексу</li>
     *   <li>Поиск элементов</li>
     *   <li>Удаление элементов</li>
     *   <li>Очистку контейнера</li>
     * </ol>
     * </p>
     */
    public static void main(String[] args) {
        // Создаем контейнер для строк
        LinkedListContainer<String> container = new LinkedListContainer<>();

        System.out.println("=== LinkedList Container Demo ===");

        // Добавляем элементы в контейнер
        container.add("Apple");
        container.add("Banana");
        container.add("Orange");
        container.add("Grape");

        // Демонстрация базового состояния контейнера
        System.out.println(container.toString());
        System.out.println("Container after adding elements: " + container);
        System.out.println("Size: " + container.size());
        System.out.println("Is empty: " + container.isEmpty());

        // Доступ к элементам по индексу
        System.out.println("\n--- Access elements by index ---");
        System.out.println("\nElement at index 1: " + container.get(1));
        System.out.println("Element at index 2: " + container.get(2));

        // Проверка наличия элементов в контейнере
        System.out.println("\n--- Check element existence ---");
        System.out.println("\nContains 'Apple': " + container.contains("Apple"));
        System.out.println("Contains 'Mango': " + container.contains("Mango"));

        // Демонстрация поиска индексов элементов
        System.out.println("\n--- Find element indexes ---");
        System.out.println("\nIndex of 'Orange': " + container.indexOf("Orange"));
        System.out.println("Index of 'Mango': " + container.indexOf("Mango"));

        // Удаление элемента по индексу
        System.out.println("\n--- Remove element by index ---");
        String removed = container.remove(1);
        System.out.println("\nRemoved element at index 1: " + removed);
        System.out.println("Container after removal: " + container);

        // Удаление элемента по значению
        System.out.println("\n--- Remove element by value ---");
        boolean wasRemoved = container.remove("Grape");
        System.out.println("'Grape' removed: " + wasRemoved);
        System.out.println("Container after removal: " + container);

        // Полная очистка контейнера
        System.out.println("\n--- Clear container ---");
        container.clear();
        System.out.println("\nContainer after clear: " + container);
        System.out.println("Size: " + container.size());
        System.out.println("Is empty: " + container.isEmpty());

        // Демонстрация завершена
        System.out.println("\n=== Demo completed successfully ===");
    }
}