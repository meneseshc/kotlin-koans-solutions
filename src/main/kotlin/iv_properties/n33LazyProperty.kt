package iv_properties

import util.TODO

class LazyProperty(val initializer: () -> Int) {
  private var counter: Int = 0
  var lazyValue: Int = 0
  //  val lazy: Int = todoTask33()
  val lazy: Int
    get() {
      if (counter == 0) {
        counter++
        lazyValue = initializer.invoke()
        return lazyValue
      } else
        return lazyValue
    }
}

fun todoTask33(): Nothing = TODO(
  """
        Task 33.
        Add a custom getter to make the 'lazy' val really lazy.
        It should be initialized by the invocation of 'initializer()'
        at the moment of the first access.
        You can add as many additional properties as you need.
        Do not use delegated properties yet!
    """,
  references = { LazyProperty({ 42 }).lazy }
)
