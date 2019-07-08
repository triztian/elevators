
/**
 * 2) Implement an immutable class in JavaScript. The class fully encapsulates an array 
 * of integers, an arbitrary data object, * and a `Date`. It should allow clients to 
 * specify all three when the class is constructed. 
 * Once instantiated, the value of * each property should not change.
 */
export default class ImmutableData {

	/**
	 * Create a new set of immutable data.
	 * 
	 * @param {[number]} numbers A plain array of numbers. 
	 * @param {object} obj 
	 * @param {Date} date 
	 */
	constructor(numbers, obj, date) {

		this.numbers = numbers.map(x => x); // copy using the identity function
		this.obj = obj;
		this.date = date;

		if (Object.freeze) {
			ImmutableData.deepFreeze(this.numbers);
			ImmutableData.deepFreeze(this.obj);
			ImmutableData.deepFreeze(this.date);
		}

	}

	get numbers() {
		return this.numbers;
	}

	get data() {
		return this.obj;
	}

	get date() {
		return this.date;
	}

	static deepFreeze(obj) {
		for(let p in Object.getOwnPropertyNames(obj)) {
			if (Array.isArray(obj[p])) {
				obj[p].map(ImmutableData.deepFreeze);
				continue;
			}

			ImmutableData.deepFreeze(obj[p]);
		}
	}

}