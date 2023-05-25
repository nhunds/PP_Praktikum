package m1.observer

interface Observer<T> {
    fun update(observableData: Observable.Data<T>)
}