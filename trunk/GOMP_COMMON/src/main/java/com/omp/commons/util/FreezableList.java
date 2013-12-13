package com.omp.commons.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 동결 가능한 리스트 객체
 * 동결된 이후에는 리스트 수정 메소드 접근시 RuntimeException 발생.
 * @author pat
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class FreezableList<E>
	extends ArrayList<E> {
	
	private boolean	isFreeze	= false;
	
	public FreezableList() {
		super();
	}

	public FreezableList(int initialCapacity) {
		super(initialCapacity);
	}

	public FreezableList(Collection<? extends E> c) {
		super(c);
	}
	
	/**
	 * 리스트를 동결 시킵니다.
	 */
	public final void freeze() {
		this.isFreeze	= true;
	}

	@Override
	public boolean add(E e) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.add(e);
	}

	@Override
	public void add(int index, E element) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.addAll(index, c);
	}

	@Override
	public void clear() {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		super.clear();
	}

	@Override
	public E remove(int index) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.remove(index);
	}

	@Override
	public boolean remove(Object o) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.remove(o);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		super.removeRange(fromIndex, toIndex);
	}

	@Override
	public E set(int index, E element) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.set(index, element);
	}

	@Override
	public void trimToSize() {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		super.trimToSize();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.containsAll(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		if (this.isFreeze) {
			throw new RuntimeException("this list freezed.");
		}
		return super.retainAll(arg0);
	}
}
