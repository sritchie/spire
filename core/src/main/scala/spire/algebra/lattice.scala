package spire.algebra

import spire.syntax.eq._

trait MeetSemilattice[A] { self =>
  def meet(x: A, y: A): A

  def partialOrder: PartialOrder[A]

  def meetAlgebra: CSemigroup[A] =
    new CSemigroup[A] { def op(x: A, y: A): A = self.meet(x, y) }
}

object MeetSemilattice {
  def apply[A](implicit ev: MeetSemilattice[A]): MeetSemilattice[A] = ev

  def meetAll[A](as: Iterable[A])(implicit s: MeetSemilattice[A]): Option[A] =
    if (as.isEmpty) None else Some(as.reduceLeft(s.meet))

  def partialOrder[A](implicit e: Eq[A], s: MeetSemilattice[A]): PartialOrder[A] =
    new PartialOrder[A] {
      def partialCompare(x: A, y: A): Double =
        if (x === y) 0.0 else {
          val l = s.meet(x, y)
          if (l === x) -1.0 else if (l === y) 1.0 else Double.NaN
        }
    }
}

trait JoinSemilattice[A] { self =>
  def join(x: A, y: A): A

  def partialOrder: PartialOrder[A]

  def joinAlgebra: CSemigroup[A] =
    new CSemigroup[A] { def op(x: A, y: A): A = self.join(x, y) }
}

object JoinSemilattice {
  def apply[A](implicit ev: JoinSemilattice[A]): JoinSemilattice[A] = ev

  def joinAll[A](as: Iterable[A])(implicit s: JoinSemilattice[A]): Option[A] =
    if (as.isEmpty) None else Some(as.reduceLeft(s.join))

  def partialOrder[A](implicit e: Eq[A], s: JoinSemilattice[A]): PartialOrder[A] =
    new PartialOrder[A] {
      def partialCompare(x: A, y: A): Double =
        if (x === y) 0.0 else {
          val u = s.join(x, y)
          if (u === x) 1.0 else if (u === y) -1.0 else Double.NaN
        }
    }
}

trait Lattice[A] extends MeetSemilattice[A] with JoinSemilattice[A]

trait BoundedLattice[A] extends Lattice[A] { self =>
  def zero: A
  def one: A

  override def meetAlgebra: CMonoid[A] =
    new CMonoid[A] {
      def id: A = self.one
      def op(x: A, y: A): A = self.meet(x, y)
    }

  override def joinAlgebra: CMonoid[A] =
    new CMonoid[A] {
      def id: A = self.zero
      def op(x: A, y: A): A = self.join(x, y)
    }
}

object BoundedLattice {
  def meetAll[A](as: Iterable[A])(implicit s: BoundedLattice[A]): A =
    as.foldLeft(s.one)(s.meet)

  def joinAll[A](as: Iterable[A])(implicit s: BoundedLattice[A]): A =
    as.foldLeft(s.zero)(s.join)

  def meetAndJoinAll[A](as: Iterable[A])(implicit s: BoundedLattice[A]): (A, A) =
    as.foldLeft((s.one, s.zero)) { case ((l, u), a) =>
      (s.meet(l, a), s.join(u, a))
    }
}
