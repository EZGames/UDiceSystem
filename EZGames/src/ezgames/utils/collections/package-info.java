/**
 *There are a few major things to notice in this collections package:
 *<ol>
 *	<li>The two new collections, {@link ezgames.utils.collections.zarray.ZArray ZArray} 
 *and {@link ezgames.utils.collections.MlList MlList}</li>
 *	<li>The {@link ezgames.utils.collections.SimpleCollection SimpleCollection} interface</li>
 *	<li>The {@link ezgames.utils.collections.Stackable Stackable} interface</li>
 *	<li>The {@link ezgames.utils.collections.rec.RandomElementChooser RandomElementChooser} class</li>
 *</ol>
 *<p> It all started with {@code ZArray}.  I wanted to use a small, fast, 
 *and immutable collection within my model objects.</p>
 *<p>It had to be small because, with the exception of dice's collection of faces,
 *A great majority of the time, collections within the system's domain objects
 *would hold one or two or <i>maybe</i> 3 objects, making something like ArrayList
 *have too much space from the get-go. I realize that you can set it's starting
 *size, but there are still the other two factors.</p>
 *<p>I wanted it to be fast, mostly when it comes to iterating over and accessing
 *elements because I want this system to be used in mobile apps.  It was allowed
 *to be a bit slower at creation, since that should only happen once in a while,
 *while iterating and accessing would be happening often.</p>
 *<p>Lastly, I wanted immutability.  Once any part of the die is created, I didn't
 *want anything to accidentally change it.  I also liked the idea of keeping the
 *the collection class extra simple by not allowing the addition or subtraction
 *of elements once it's created.</p>
 *<p>Because of all of this, the {@code ZArray} didn't fit well with Java's
 *{@code Collection} interface. So, for a while, I was just passing around 
 *{@code Iterable}s.  But that led to having a utility class that would do
 *the {@code contains(), get(), indexOf(),} and {@code size()} methods
 *from the {@code Iterable}s.  These were highly inefficient, iterating over
 *the collection for everything.</p>
 *<p>Thus the {@code SimpleCollection} interface was born.  It provided the
 *previously mentioned methods, plus methods to supply the collections' 
 *{@code Iterator}s and Java 8 {@code Stream}s.  Lastly, it has a
 *static method that transforms any type of Java collection or {@code Iterable}
 *into a {@code SimpleCollection}.</p>
 *<p>With this, I was able to provide one unified collection interface throughout
 *the UDice System without having to deal with the lowest common denominator
 *of just {@code Iterable}.  It also makes the collections effectively
 *immutable, except in the case of the elements being mutated from outside the
 *collection.</p>
 *<p>I also created another collection, called {@code MlList} to allow me
 *to build collections easily and immutably.  It's named MlList because I "stole"
 *the idea from a language called ML, a functional programming language.  It
 *implements the {@code SimpleCollection} interface, too, so it can be used
 *within the system.  {@code MlList}, alongside the {@code Stackable}
 *interface, allows for quick, easy, and fluent creation of collections.  See
 *{@link ezgames.utils.collections.Stackable Stackable} for more information on
 *that.</p>
 *<p>Lastly, we have {@code RandomElementChooser}.  This class was created
 *to decouple the choosing of random faces on a die from the {@code Die}
 *classes.  It works with any {@code SimpleCollection} to randomly choose
 *an element from it. It uses dependency injection so you can provide your own
 *random number generator (implementing the {@link ezgames.math.random.SimpleRandom 
 *SimpleRandom} interface) as well as any rules you want the chosen elements to
 *follow, using the Strategy Pattern. It even supports weighted randomization 
 *through the use of weighted collections (see {@link ezgames.util.Weighted}). 
 *Check out {@link ezgames.utils.collections.rec.RandomElementChooser 
 *RandomElementChooser} for more details.</p>
 */
package ezgames.utils.collections;

