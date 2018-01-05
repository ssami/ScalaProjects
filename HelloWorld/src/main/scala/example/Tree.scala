package example

/**
  * Case classes are used to write convenient,
  * boilerplate code for objects that can be
  * compared to each other, since they come
  * with equals and hash methods
  * and are automatically compared based on
  * content, and not on reference ('==')
  *
  * 1) don't need "new" for construction. why? see "apply"
  * 2) getter functions automatically defined for member variables
  * 3) default definitions of equals and hash mean we can compare by value, not by identity
  * 4) default "tostring" provided
  * 5) pattern matching -- need to check
  */

class Tree {

}
