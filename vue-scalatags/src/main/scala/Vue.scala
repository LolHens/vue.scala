import scalatags.Text.all._

/**
  * Created by pierr on 29.05.2017.
  */
object Vue {
  def bind(attr: Attr): Attr = attr.copy(s":${attr.name}")

  def listener(listener: () => (), name: String) = s"@..."

  def main(args: Array[String]): Unit =
    println(
      div(
        bind(`class`) := "test"
      )
        .render
    )
}
