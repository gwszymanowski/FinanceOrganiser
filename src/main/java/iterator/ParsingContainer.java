package iterator;

import service.CategoryService;
import service.CrudServiceI;
import service.ItemService;
import service.SheetRowService;

public class ParsingContainer implements Container {

	@SuppressWarnings("rawtypes")
	private final CrudServiceI[] services = { new CategoryService(), new ItemService(), new SheetRowService(false),
			new SheetRowService(true) };
	private boolean[] isAllowed;

	public ParsingContainer(boolean[] isAllowed) {

		if (isAllowed.length != 4)
			throw new IllegalArgumentException("The constructor's array must have 4 arguments!");

		this.isAllowed = isAllowed;
	}

	@Override
	public Iterator getIterator() {
		return new ParsingIterator(this.isAllowed);
	}

	private class ParsingIterator implements Iterator {

		int index;
		boolean[] isAllowed;

		ParsingIterator(boolean[] isAllowed) {
			this.isAllowed = isAllowed;
		}

		@Override
		public boolean hasNext() {
			isAllowed();

			if (index < isAllowed.length)
				return true;

			return false;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return services[index++];
			}

			return null;
		}

		private void isAllowed() {
			while (this.isAllowed[index] != true)
				index++;
		}

	}

}
