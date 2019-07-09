import React, {useState} from 'react'
import './css/FloorSelector.css'

export default function FloorSelector(props) {

	const [selectedFloor, setSelectedFloor] = useState(props.selectedFloor);

	const onChange = e => {
		setSelectedFloor(e.target.value);
		props.onSelectFloor(e.target.value);
	}

	return (
		<div className="FloorSelector">
			<label htmlFor={`FS${props.floorNumber}`}>Destination</label>
			<select id={`FS${props.floorNumber}`} onChange={onChange} value={selectedFloor}>
				<option value="-1">None</option>
				{props.floors.map(f => {
					return (<option key={`fo${f.number}`} value={f.number}>{f.name}</option>);
				})}
			</select>
		</div>
	);
}