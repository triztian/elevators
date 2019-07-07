/**
 * 
 */
class ElevatorAPI {

	/**
	 * Returns a promise that when resolved contains an array of the 
	 * the floors
	 */
	listFloors() {
		return new Promise((resolve, reject) => {
			// TODO: Perform actual API call
			resolve([
				{
					name: 'Ground',
					number: 0
				},
				{
					name: 'First',
					number: 1
				}
			]);
		});
	}

	/**
	 * 
	 * @param {*} from 
	 * @param {*} to 
	 */
	callElevator(from, to) {

		let endpoint = `http://localhost:9000/floor/${from}`;
		if (Number.isInteger(to)) {
			endpoint += `/to/${to}`
		}
		
		return fetch(endpoint, { 
			method: 'POST' 
		});

	}

	/**
	 * Returns a promise that when successful it 
	 * @param {*} onMessage The function to be called on any message
	 */
	listenTo(onMessage) {
		
	}
}

let api = new ElevatorAPI();

export default api;