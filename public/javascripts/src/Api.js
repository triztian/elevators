/**
 * 
 */
class ElevatorAPI {

	/**
	 * Returns a promise that when resolved contains an array of the 
	 * the floors.
	 */
	async listFloors() {
		const endpoint = `https://mcmelevators.herokuapp.com/floors`
		return fetch(endpoint, {mode: 'cors'}).then(r => r.json());
	}

	/**
	 * Fetch the status of an elevator.
	 */
	async getElevator(id) {
		const endpoint = `https://mcmelevators.herokuapp.com/elevator/${id}`;
		return fetch(endpoint, {mode: 'cors'}).then(r => r.json());
	}

	/**
	 * @param {number} from The floor to which the elevator is being called from.
	 * @param {number} to An optional destionation floor.
	 */
	async callElevator(from, to) {

		if (!Number.isInteger(parseInt(from))) {
			return new Error("from argument must be a valid number");
		}

		let endpoint = `https://mcmelevators.herokuapp.com/floor/${from}`;

		if (to !== null && to !== undefined && Number.isInteger(parseInt(to))) {
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

		const endpoint = `wss://mcmelevators.herokuapp.com/elevator/${id}/updates`;

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

				console.log("api.watchElevator", elevator);

				// FIXME: This filtering should happen on the server.
				if (`E${id}` === elevator.id) {
					onUpdate(elevator);
				}
			}
		});
	}
}

let api = new ElevatorAPI();

export default api;