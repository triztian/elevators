/**
 * 
 */
export default class ElevatorAPI {

	/**
	 * Returns a promise that when resolved contains an array of the 
	 * the floors
	 */
	listFloors() {
		return new Promise((resolve, reject) => {
			resolve([
				{
					name: 'Ground',
					number: 0
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

	}

	/**
	 * Returns a promise that when successful it 
	 * @param {*} onMessage The function to be called on any message
	 */
	listenTo(onMessage) {
		
	}
}