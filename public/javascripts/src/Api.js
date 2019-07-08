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
		return fetch(endpoint, {mode: 'cors'}).then(r => r.json());
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

		let id = elevatorID;
		if (elevatorID.startsWith('E')) {
			id = elevatorID.substring(1);
		}

		const endpoint = `ws://localhost:9000/elevator/${id}/updates`;

		return new Promise((resolve, reject) => {
			let ws = new WebSocket(endpoint, '*');

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

				let elevator = JSON.parse(evt.data);

				// FIXME: This filtering should happen on the server.
				if (`E${id}` === elevator.ID) {
					onUpdate(elevator);
				}
			}
		});
	}
}

let api = new ElevatorAPI();

export default api;