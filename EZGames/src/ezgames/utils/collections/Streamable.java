package ezgames.utils.collections;

import java.util.stream.Stream;

public interface Streamable<E>
{
	Stream<E> stream();
}
