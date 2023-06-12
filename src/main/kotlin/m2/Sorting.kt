package m2

class Sorting {
    fun<A> sort(list: MutableList<A>, ord: Ordering<A>) {
        if (list.size <= 1) return

        var sorted = false
        var tmp: A?

        while (!sorted) {
            sorted = true
            for (i in 0 until list.lastIndex) {
                val left = list[i]
                val right = list[i + 1]

                if (ord(left)(right) == OrderResult.Higher) {
                    tmp = left
                    list[i] = right
                    list[i + 1] = tmp
                    sorted = false
                }
            }
        }
    }
}