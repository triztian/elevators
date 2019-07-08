import React, {useState} from 'react';

import CallButton from './CallButton'
import FloorSelector from './FloorSelector'
import './ControlPanel.css'

import ElevatorAPI from './ElevatorAPI/Api'

const noneSelectedFloor = -1;

export default function ControlPanel(props) {

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
				selectedFloor={noneSelectedFloor}
				onSelectFloor={setDestFloor}
			/> 
			<CallButton 
				floorNumber={props.floorNumber} 
				onCallElevator={onCallElevator}
			/>
		</div>
	)
}
