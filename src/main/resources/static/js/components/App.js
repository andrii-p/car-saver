const App = ({cars = []}) =>
    <div>
        <button type="button" className="btn btn-warning">Primary</button>
        <ul>
            {cars.map((car, i) =>
                <li key={i}>{car.adName} : {car.price}</li>
            )}
        </ul>

    </div>

export default App
