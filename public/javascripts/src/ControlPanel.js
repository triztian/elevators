import React, {useState} from 'react';

import CallButton from './CallButton'
import FloorSelector from './FloorSelector'

import ElevatorAPI from './ElevatorAPI/Api'

export default function ControlPanel(props) {

	// let api = new ElevatorAPI()

	const [destFloor, setDestFloor] = useState(null);

	const onCallElevator = () => {

		let from = props.floorNumber
		let to = destFloor;

		console.log('Elevator called: from: ', from, 'to: ', to)

		ElevatorAPI.callElevator(from, to).then((resp) => {
			console.log('onCallElevator: success: ', resp);
		}).catch(err => {
			console.error('onCallElevator: ', err);
		});

	}

	return (
		<div className="ControlPanel">
			<FloorSelector 
				floors={props.floors}
				selectedFloor={0}
				onSelectFloor={setDestFloor}
			/> 
			<CallButton 
				floorNumber={props.floorNumber} 
				onCallElevator={onCallElevator}
			/>
		</div>
	)
}
