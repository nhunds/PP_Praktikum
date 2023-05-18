package vorlesung.iterator/*
- Implementiert den LinkedListIterator, welcher die Iteration über eine verkettete Liste kapselt
- Der LinkedListIterator nimmt immer den Startknoten (first) entgegen und verändert diesen im
Laufe der Iteration
- Testet eure Implementierung, indem ihr die Collection der Studierenden zwischen List, LinkedList,
Set und anderen Iterables wechselt
- Hausaufgabe: Implementiert einen Iterator namens ReverseLinkedListIterator, welcher die verkettete
Liste rückwärts durchläuft
*/
class LinkedListIterator<A>(root: LinkedList.Node<A>?) : Iterator<A> {
    var run = root

    override fun hasNext(): Boolean = run != null

    override fun next(): A {
        val data = run!!.data

        run = run!!.next

        return data
    }
}

class LinkedList<A>(vararg elements: A) : Iterable<A> {
    data class Node<A>(val data: A, var next: Node<A>?)

    var first: Node<A>? = null

    init {
        for (elem in elements) {
            addLast(elem)
        }
    }

    fun isEmpty(): Boolean =
        first == null

    fun addFirst(data: A) {
        first = Node(data, first)
    }

    fun addLast(data: A) {
        if (isEmpty()) {
            addFirst(data)
            return
        }

        var runPointer = first
        while (runPointer?.next != null) {
            runPointer = runPointer.next
        }

        runPointer?.next = Node(data, null)
    }

    override fun iterator(): Iterator<A> {
        return LinkedListIterator(first)
    }
}

class Course(private val students: Iterable<String>) {

    fun numberOfStudents(): Int {
        var count = 0
        for (student in students) {
            count += 1
        }
        return count
    }

    fun printEachStudent() {
        for (student in students) {
            println(student)
        }
    }
}

fun main() {
    val students = LinkedList("Maja", "Anna", "Fridolin")
    // val students = listOf("Maja", "Anna", "Fridolin")
    // val students = setOf("Maja", "Anna", "Fridolin")
    val course = Course(students)
    course.printEachStudent()
    println("number of students: ${course.numberOfStudents()}")
}