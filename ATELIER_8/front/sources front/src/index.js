import { createRoot } from 'react-dom/client'

import './index.css'

import App from './Components/App/App'



// Fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { fal } from 'fontawesome-pro-light-svg-icons'
library.add(fal)



createRoot(document.getElementById('root')).render(<App />)