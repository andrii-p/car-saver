import data from '../../../data/cars.json'

const Test = () =>
    <div>
        <button type="button" className="btn btn-warning">Primary</button>
        <ul>
            {
                data.cars.map((car, i) =>
                <li key={i}>{car.adName} : {car.price}</li>
            )}
        </ul>

    </div>

export default Test