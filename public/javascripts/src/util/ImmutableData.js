
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

		this._numbers = numbers.map(x => x); // copy using the identity function
		this._obj = obj;
		this._date = date;

		if (Object.freeze) {
			ImmutableData.deepFreeze(this._numbers);
			ImmutableData.deepFreeze(this._obj);
		}

	}

	get numbers() {
		return this._numbers;
	}

	get data() {
		return this._obj;
	}

	get date() {
		return new Date(this._date.valueOf());
	}

	/**
	 * Recursively freezes the given object's properties, when an object 
	 * is frozen an exception will be thrown if an property is modified.
	 * @param {any} obj The object to be deeply freezed.
	 */
	static deepFreeze(obj) {

		// console.log('deepFreeze: ', obj);

		if (obj === null) return;
		if (obj === undefined) return;

		for (let p of Object.keys(obj)) {

			// console.log('deepFreeze: prop: ', p, "object: ", obj[p])

			if (Array.isArray(obj[p])) {
				for(let i = 0; i < obj[p].length; i++)
					ImmutableData.deepFreeze(obj[p][i]);
				continue;
			}

			if (obj[p] === Object(obj[p])) {
				ImmutableData.deepFreeze(obj[p]);
			}

			Object.freeze(obj[p]);
		}

		Object.freeze(obj);

	}

}