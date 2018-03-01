package tp05.util

private[util] trait AbstractReactor {
  private[util] def react(prop : Property, value : Any) : Unit
}