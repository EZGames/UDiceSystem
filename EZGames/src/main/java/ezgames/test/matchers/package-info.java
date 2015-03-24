/**
 * <p>
 * These matchers come with alternate {@code @Factory} methods that replace the
 * need for is(not()) (and therefore the need to follow the grammar of is(...).</p>
 * <p>
 * While I feel that it was a decent and very clever idea to do the is() and 
 * is(not()) paradigm, I prefer to<br>
 * 1) not nest methods like that (they dirty up the code a bit) and<br>
 * 2) not use the "is ____" grammatical structure when better options are 
 * avialable<br>
 * "assert that statement is throwing an exception" is just a weaker way of 
 * saying "assert that statement throws an exception".
 */
package ezgames.test.matchers;
