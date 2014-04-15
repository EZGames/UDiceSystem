package com.ezgames.utils.interfaces;

/**
 * <p>The {@code Immutable} interface is a tag interface that signifies that 
 * objects of the implementing class do not change state once it is set. Any
 * state changes are reflected in a new place in memory and do not actually
 * make any changes to the original place in memory.</p>
 * <p>To learn more about immutability, go to 
 * {@linkplain http://www.javapractices.com/topic/TopicAction.do?Id=29}.</p>
 * <p>To make an Immutable object, you must follow certain guidelines:</p>
 * <ul>
 * <li>If there are any setter methods, it must return a newly created object 
 * with the change.  This library contains very few setter methods - factory 
 * methods take care of most creation.</li>
 * <li>When implementing getter methods, if the object being returned is not 
 * immutable, return a copy of it.</li>
 * <li>In your constructors, if an object that was passed in is not immutable, 
 * you must make a copy of it to be used as your field.  Otherwise, that object 
 * is able to be changed from outside the class.</li>
 * </ul> 
 */
public interface TImmutable {}
