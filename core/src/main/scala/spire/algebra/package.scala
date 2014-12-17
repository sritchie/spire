package spire

package object algebra {

  type Eq[A] = _root_.algebra.Eq[A]
  val Eq = _root_.algebra.Eq

  type PartialOrder[A] = _root_.algebra.PartialOrder[A]
  val PartialOrder = _root_.algebra.PartialOrder

  type Order[A] = _root_.algebra.Order[A]
  val Order = _root_.algebra.Order

  type Semigroup[A] = _root_.algebra.Semigroup[A]
  val Semigroup = _root_.algebra.Semigroup

  type CSemigroup[A] = _root_.algebra.CommutativeSemigroup[A]
  val CSemigroup = _root_.algebra.CommutativeSemigroup

  type Monoid[A] = _root_.algebra.Monoid[A]
  val Monoid = _root_.algebra.Monoid

  type CMonoid[A] = _root_.algebra.CommutativeMonoid[A]
  val CMonoid = _root_.algebra.CommutativeMonoid

  type Group[A] = _root_.algebra.Group[A]
  val Group = _root_.algebra.Group

  type AbGroup[A] = _root_.algebra.CommutativeGroup[A]
  val AbGroup = _root_.algebra.CommutativeGroup

  type Signed[A] = _root_.algebra.number.Signed[A]
  val Signed = _root_.algebra.number.Signed

  type Sign = _root_.algebra.number.Sign
  val Sign = _root_.algebra.number.Sign
}
