/**
 * 
 */
class ElevatorAPI {

	/**
	 * Returns a promise that when resolved contains an array of the 
	 * the floors
	 */
	async listFloors() {
		const endpoint = `http://localhost:9000/floors`
		return fetch(endpoint).then(r => r.json());
	}

	/**
	 * @param {number} from The floor to which the elevator is being called from.
	 * @param {number} to An optional destionation floor.
	 */
	async callElevator(from, to) {

		let endpoint = `http://localhost:9000/floor/${from}`;

		if (Number.isInteger(to)) {
			endpoint += `/to/${to}`
		}
		
		return fetch(endpoint, { 
			method: 'POST',
			mode: 'cors'
		}).then(r => r.json());

	}

	/**
	 * Will watch updates to a specific elevator, and when an update
	 * happens it will call the `onUpdate` function.
	 * @param {*} onUpdate The function to be called on any message
	 */
	watchElevator(elevatorID, onUpdate) {
		return new Promise((resolve, reject) => {
			let ws = new WebSocket(`http://localhost:9000/elevator/${elevatorID}/updates`, '*');

			const stop = () => {
				ws.close()
			}

			ws.onopen = () => {
				resolve(stop);
			}

			ws.onerror = err => {
				reject(err);
			}

			ws.onmessage = evt => {
				onUpdate(JSON.parse(evt.data));
			}
		});
	}
}

let api = new ElevatorAPI();

export default api;