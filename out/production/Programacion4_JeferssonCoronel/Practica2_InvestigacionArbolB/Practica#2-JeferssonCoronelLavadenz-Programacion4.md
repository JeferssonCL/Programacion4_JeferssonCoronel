**Nombre:** Jefersson Jhovani Coronel Lavadenz** 

# Practica 2

1. **¿Cuál es la diferencia entre un árbol binario, un árbol de búsqueda binaria y un árbol AVL?** 

- **Árbol binario:** Es una estructura de datos en la que cada nodo tiene a lo sumo dos hijos: izquierdo y derecho. Puede tener un número variable de niveles y no tiene restricciones sobre el orden de los elementos almacenados en él. 

- **Árbol de búsqueda binaria (BST):** Es un tipo de árbol binario en el que cada nodo tiene una clave y cumple con la propiedad de que el valor de todas las claves en el subárbol izquierdo es menor que el valor de la clave en el nodo actual, mientras que el valor de todas las claves en el subárbol derecho es mayor. Esto permite una búsqueda eficiente.

- **Árbol AVL:** Es una forma especial de árbol de búsqueda binaria en la que se asegura que la diferencia de alturas entre los subárboles izquierdo y derecho de cada nodo (conocida como factor de equilibrio) no sea mayor que 1. Si en algún momento se viola esta condición, se realizan rotaciones en el árbol para reequilibrarlo y mantener su altura logarítmica, lo que garantiza tiempos de búsqueda y otras operaciones eficientes.

2. **¿Cuáles son las ventajas y desventajas de usar un árbol en lugar de una lista enlazada o un arreglo?** 

**Ventajas del árbol:**

- Búsqueda eficiente: En árboles de búsqueda binaria y árboles AVL, la búsqueda de elementos se realiza en tiempo logarítmico, lo que es mucho más rápido que una búsqueda lineal en una lista enlazada o un arreglo no ordenado.

- Inserción y eliminación eficiente: Los árboles balanceados (como el AVL) ofrecen tiempos de inserción y eliminación logarítmicos, mientras que en una lista enlazada o un arreglo, estas operaciones pueden ser costosas.

- Estructura jerárquica: Los árboles permiten organizar datos de forma jerárquica, lo que puede ser útil para representar relaciones entre elementos.

**Desventajas del árbol:**

- Mayor consumo de memoria: Los árboles requieren más memoria que las listas enlazadas para almacenar punteros adicionales.

- Mayor complejidad de implementación: La lógica de un árbol es más compleja que una lista enlazada o un arreglo simple, lo que puede llevar a errores en la implementación.

- Necesidad de mantener el equilibrio: En árboles balanceados, se requiere realizar operaciones adicionales para mantener el equilibrio, lo que puede aumentar el costo de inserción y eliminación.

3. **¿Cómo realizar el recorrido en profundidad (DFS) y recorrido en anchura (BFS) en un árbol binario?** 

**Recorrido en profundidad (DFS):** Hay tres formas comunes de realizar DFS en un árbol binario: 

- **Preorden (Nodo - Izquierdo - Derecho):** Visitar el nodo actual, luego recorrer el subárbol izquierdo y finalmente recorrer el subárbol derecho.

- **Inorden (Izquierdo - Nodo - Derecho):** Recorrer el subárbol izquierdo, luego visitar el nodo actual y finalmente recorrer el subárbol derecho.

- **Postorden (Izquierdo - Derecho - Nodo):** Recorrer el subárbol izquierdo, luego recorrer el subárbol derecho y finalmente visitar el nodo actual.

**Recorrido en anchura (BFS):** Se realiza nivel por nivel, visitando todos los nodos de  un nivel antes de pasar al siguiente nivel. Para hacerlo, se utiliza una estructura de datos como una cola para mantener los nodos a visitar.

4. **¿Cómo equilibrar un árbol binario para asegurar un tiempo de búsqueda eficiente?** 

Para equilibrar un árbol binario y asegurar un tiempo de búsqueda eficiente, se pueden  utilizar distintos métodos. Uno de los más conocidos es el algoritmo de inserción y eliminación en árboles AVL, que realiza rotaciones en el árbol para mantener su equilibrio: 

**Rotación simple a la izquierda (Left Rotation)**: Se realiza cuando el subárbol derecho es más alto que el subárbol izquierdo por dos unidades o más. Consiste en hacer que el nodo desequilibrado sea el hijo izquierdo del nodo que está a su derecha. 

**Rotación simple a la derecha (Right Rotation)**: Se realiza cuando el subárbol izquierdo es más alto que el subárbol derecho por dos unidades o más. Consiste en hacer que el nodo desequilibrado sea el hijo derecho del nodo que está a su izquierda.

**Rotación doble izquierda-derecha (Left-Right Rotation)**: Se realiza cuando el subárbol derecho es más alto que el subárbol izquierdo por dos unidades o más y el hijo derecho del nodo desequilibrado también tiene un subárbol izquierdo más alto que el subárbol derecho. Consiste en aplicar una rotación a la derecha y luego una rotación a la izquierda.

**Rotación doble derecha-izquierda (Right-Left Rotation)**: Se realiza cuando el subárbol izquierdo es más alto que el subárbol derecho por dos unidades o más  y el hijo izquierdo del nodo desequilibrado también tiene un subárbol derecho más alto que el subárbol izquierdo. Consiste en aplicar una rotación a la izquierda y luego una rotación a la derecha.

Estas rotaciones se aplican de manera recursiva hasta llegar al nodo raíz del árbol, manteniendo el equilibrio en cada paso.

5. **¿Cuál es el algoritmo para encontrar el ancestro común más bajo (LCA) en un árbol?** 

El LCA de dos nodos en un árbol es el nodo más bajo que está comúnmente presente  en el camino desde el nodo raíz hasta ambos nodos. Uno de los algoritmos eficientes  para encontrar el LCA es el algoritmo de "Euler Tour" con "Sparse Table". 

Paso a paso:

1) Realizar un recorrido en profundidad (DFS) en el árbol para obtener el recorrido de Euler (secuencia de nodos visitados) y sus profundidades.
1) Construir una estructura de datos llamada "Sparse Table" para responder consultas de rango mínimo en el recorrido de Euler en tiempo constante.
1) Cuando se quiere encontrar el LCA de dos nodos, primero se obtiene su intervalo en el recorrido de Euler y luego se realiza una consulta de rango mínimo en el Sparse Table para encontrar el nodo con la menor profundidad en ese intervalo.

Este algoritmo tiene una complejidad de tiempo de O(n) para la construcción del recorrido de Euler y el Sparse Table, y luego las consultas del LCA se realizan en tiempo constante O(1). 

6. **¿Cómo implementar un árbol de sufijos para realizar búsquedas eficientes en cadenas de texto?** 

Un árbol de sufijos es una estructura de datos que se utiliza para almacenar todas las subcadenas de una cadena dada y permite realizar búsquedas de patrones en tiempo lineal. La construcción de un árbol de sufijos se puede hacer utilizando algoritmos como el algoritmo Ukkonen o el algoritmo de construcción de árbol de sufijos de McCreight. 

Pasos para construir un árbol de sufijos:

1) Agregar el carácter especial (como '$') al final de la cadena para asegurarse de que cada sufijo sea único.
1) Inicializar un árbol con un único nodo raíz.
1) Iterar sobre cada carácter de la cadena y agregar sufijos al árbol de sufijos, creando nuevos nodos y enlaces según sea necesario.
1) Repetir el proceso para cada sufijo de la cadena.

Una vez que se ha construido el árbol de sufijos, se puede utilizar para buscar patrones en la cadena original en tiempo lineal, y también se pueden resolver problemas como contar el número de ocurrencias de un patrón, buscar el patrón más largo que se repite y encontrar el sufijo más largo común entre varias cadenas, entre otros.

7. **¿Cómo se puede utilizar un árbol de decisión para clasificar datos en problemas de aprendizaje automático?** 

Un árbol de decisión es un modelo de aprendizaje supervisado utilizado para clasificar  o predecir datos. El árbol de decisión toma decisiones basadas en reglas "if-then-else", donde cada nodo interno representa una característica o atributo y cada borde o rama representa una decisión o resultado posible basado en el valor de esa característica. 

Pasos para utilizar un árbol de decisión en clasificación:

1) Preparar el conjunto de datos de entrenamiento, que consiste en características y etiquetas/clases para cada ejemplo.
1) Seleccionar una métrica de impureza (como Gini impurity o entropía) para medir la pureza de los nodos durante la construcción del árbol.
1) Construir el árbol de decisión dividiendo los nodos en cada nivel según la característica que maximice la reducción de impureza.
1) Continuar dividiendo los nodos hasta alcanzar un criterio de parada, como una profundidad máxima, un número mínimo de ejemplos en un nodo o alcanzar nodos puros (todos los ejemplos pertenecen a una clase).
1) Una vez construido el árbol, se puede utilizar para clasificar nuevos datos recorriendo el árbol desde la raíz y siguiendo las decisiones basadas en las características del dato a clasificar.
8. ¿**Cómo utilizar un árbol para representar y organizar una jerarquía de datos, como en la organización de archivos en un sistema de archivos?** 

Un árbol se utiliza comúnmente para representar una jerarquía de datos, como en la organización de archivos en un sistema de archivos. En este contexto, cada nodo del árbol representa un directorio o una carpeta, y las aristas o ramas representan las relaciones entre los directorios (por ejemplo, un directorio contiene otro). 

Por ejemplo, en un sistema de archivos, el nodo raíz representa el directorio principal, y los nodos secundarios representan subdirectorios y archivos. Cada subdirectorio puede contener más subdirectorios o archivos, lo que forma una estructura jerárquica. 

El uso de un árbol para organizar la jerarquía de archivos permite una búsqueda eficiente de archivos, ya que se pueden utilizar algoritmos de recorrido de árboles, como la búsqueda en profundidad (DFS) o la búsqueda en anchura (BFS), para encontrar archivos o navegar por la estructura de manera rápida. Además, facilita la gestión de permisos y la organización lógica de los datos en el sistema de archivos. 

9. **¿Cuál es la diferencia entre un árbol n-ario y un árbol binario? ¿En qué casos es preferible usar uno sobre el otro?** 

Un árbol n-ario es un tipo de árbol en el que cada nodo puede tener hasta n hijos. Por  otro lado, un árbol binario es un tipo especial de árbol n-ario donde cada nodo puede  tener como máximo dos hijos, conocidos como el hijo izquierdo y el hijo derecho. 

Diferencia clave:

- En un árbol n-ario, cada nodo puede tener hasta n hijos, lo que significa que puede haber más de dos hijos en cada nodo.
- En un árbol binario, cada nodo puede tener como máximo dos hijos, lo que significa que cada nodo puede tener a lo sumo un hijo izquierdo y un hijo derecho.

Cuándo usar uno sobre el otro:

- Un árbol n-ario se prefiere cuando un nodo puede tener más de dos hijos, como en situaciones donde la jerarquía no está restringida por el número de hijos en cada nodo.
- Un árbol binario es preferible cuando se desea mantener una jerarquía de dos vías, lo que es común en muchas estructuras de datos y algoritmos. 


También es más fácil de implementar y visualizar en comparación con árboles n-arios más complejos.

10. **¿Cuál es la complejidad temporal de insertar y eliminar un elemento en un árbol de búsqueda binaria promedio y en el peor caso?** 

En un árbol de búsqueda binaria promedio con n nodos, la complejidad temporal para insertar y eliminar un elemento es O(log n) en el caso promedio, dado que el árbol está equilibrado y su altura es logarítmica. 

En el peor caso, cuando el árbol de búsqueda binaria está desequilibrado (como una lista enlazada), la complejidad temporal para insertar y eliminar un elemento puede ser O(n), donde n es el número de nodos en el árbol. Esto puede ocurrir si los elementos se insertan o eliminan de manera ordenada, lo que crea un árbol de altura lineal.

11. **¿Cuál es la diferencia entre un árbol de búsqueda binaria y un árbol de búsqueda binaria equilibrada? ¿Por qué es importante el balanceo en los árboles?** 

Un árbol de búsqueda binaria es una estructura de datos en la que cada nodo tiene a lo  sumo dos hijos, y para cada nodo, todos los nodos en el subárbol izquierdo tienen  claves menores y todos los nodos en el subárbol derecho tienen claves mayores. 

Un árbol de búsqueda binaria equilibrada es un tipo especial de árbol de búsqueda binaria que se mantiene equilibrado en términos de su altura, lo que significa que la diferencia de altura entre sus subárboles izquierdo y derecho está limitada. Esto se logra mediante operaciones de rotación y reequilibrio cada vez que se inserta o elimina un nodo, asegurando que el árbol se mantenga balanceado y la complejidad siga siendo O(log n) para las operaciones.

Importancia del balanceo en los árboles:

El balanceo en los árboles, especialmente en los árboles de búsqueda binaria, es esencial para garantizar que las operaciones de búsqueda, inserción y eliminación tengan una complejidad logarítmica (O(log n)). Si el árbol no está equilibrado y se convierte en un árbol desequilibrado, las operaciones pueden tomar tiempo lineal (O(n)) en el peor caso, lo que anularía la ventaja de usar un árbol de búsqueda binaria para una búsqueda eficiente. 

12. **¿Cómo se puede realizar una recorrida en profundidad (DFS) en un árbol n-ario utilizando recursión?** 

Pseudocódigo para DFS recursivo en un árbol n-ario: 

![](Aspose.Words.afe2c38f-3761-454f-b1ac-a8e5e6400b5e.001.png)

Explicación: 

1. La función dfs toma un nodo como argumento y realiza la operación deseada en el nodo actual. 
1. Luego, la función se llama recursivamente para cada hijo del nodo en el orden en que aparecen en la lista de children. Esto garantiza que se visite todo el árbol en profundidad primero.

Recorrer el árbol en profundidad permite visitar todos los nodos del árbol de manera secuencial, lo que es útil para realizar operaciones como búsqueda, impresión de nodos, entre otras tareas. Si se desea cambiar el orden de recorrido (preorden, postorden o inorden), simplemente se debe ajustar el lugar donde se realiza la operación en el nodo actual dentro de la función dfs. 

13. **¿Qué es un grafo y cuáles son sus componentes básicos?** 

Un grafo es una estructura matemática que consta de dos componentes básicos:

- Los vértices (también llamados nodos) y aristas (también llamadas bordes). 
- Los grafos se utilizan para representar relaciones y conexiones entre diferentes elementos. 

Componentes básicos de un grafo:

- **Vértices (nodos):** Son los elementos individuales del grafo y se representan como puntos o círculos. Cada vértice puede tener una etiqueta o valor asociado.
- **Aristas (bordes):** Son las conexiones entre los vértices y se representan como líneas o flechas. Cada arista puede tener un peso o costo asociado en grafos ponderados.

14. **¿Cuál es la diferencia entre un grafo dirigido y un grafo no dirigido?** 
- **Grafo dirigido:** En un grafo dirigido, las aristas tienen una dirección asociada, lo que significa que la conexión entre los vértices tiene un sentido unidireccional. Si hay una arista que va desde el vértice A al vértice B, no necesariamente hay una arista que va desde B hacia A. 
- **Grafo no dirigido:** En un grafo no dirigido, las aristas no tienen dirección, lo que significa que la conexión entre los vértices es bidireccional. Si existe una arista entre los vértices A y B, también hay una arista entre B y A.

En términos visuales, en un grafo dirigido las aristas se representan con flechas que indican la dirección, mientras que en un grafo no dirigido las aristas se representan con líneas sin flechas.

15. **¿Cuál es la importancia de los algoritmos de búsqueda en grafos, como BFS (Breadth-First Search) y DFS (Depth-First Search)?** 

Los algoritmos de búsqueda en grafos, como BFS y DFS, son fundamentales en la teoría de grafos y tienen una amplia variedad de aplicaciones en ciencias de la computación y otras disciplinas. 

- **BFS (Breadth-First Search):** Este algoritmo se utiliza para recorrer o buscar en un grafo de manera más ancha. Comienza desde el nodo raíz y explora todos los vecinos a una distancia de 1 antes de moverse a los vecinos a distancia 2, y así sucesivamente. BFS es útil para encontrar la ruta más corta entre dos nodos en un grafo no ponderado y para recorrer el grafo en niveles. 
- **DFS (Depth-First Search):** Este algoritmo se utiliza para recorrer o buscar en un grafo de manera más profunda. Comienza desde el nodo raíz y sigue explorando tanto como sea posible a lo largo de cada rama antes de retroceder. DFS es útil para encontrar ciclos en un grafo, para generar árboles de expansión mínima en grafos ponderados y para resolver problemas relacionados con estructuras recursivas.

Estos algoritmos son esenciales en la resolución de diversos problemas en ciencias de la computación, como búsqueda en gráficos de redes sociales, rutas más cortas en mapas, resolución de laberintos, análisis de conectividad en redes, etc.

16. **¿Qué es un grafo ponderado y qué aplicaciones tiene en la vida real?** 

Un grafo ponderado es un tipo de grafo en el que las aristas tienen un valor asociado llamado "peso" o "costo". Estos pesos pueden representar distancias, tiempos, costos económicos o cualquier otra magnitud que sea relevante para el contexto del problema que se esté resolviendo. 

Aplicaciones de grafos ponderados en la vida real:

- **Redes de transporte:** Los grafos ponderados pueden utilizarse para representar redes de carreteras, ferrocarriles, rutas de vuelo, etc., donde los pesos pueden indicar distancias o tiempos de viaje.
- **Redes de comunicación:** En las redes de comunicación, los grafos ponderados pueden representar el costo de enviar datos entre diferentes nodos, lo que ayuda a encontrar rutas óptimas y minimizar la latencia.
- **Sistemas de navegación:** En aplicaciones de navegación y mapas, los grafos ponderados se utilizan para encontrar la ruta más corta entre dos ubicaciones, teniendo en cuenta el tiempo o la distancia.
- **Redes sociales:** Los grafos ponderados se utilizan para representar conexiones sociales, donde los pesos pueden indicar la fuerza de la amistad o la frecuencia de interacción entre individuos.
- **Algoritmos de optimización:** Los grafos ponderados se aplican en algoritmos de optimización combinatoria para encontrar soluciones óptimas en problemas de asignación, planificación, programación y muchos otros dominios.

17. **¿Cómo se puede determinar si un grafo es conexo y qué es una componente conexa?** 

Un grafo se considera conexo si hay un camino entre cualquier par de vértices en el grafo. Una componente conexa es un subconjunto de vértices y aristas en un grafo que forma un grafo conexo en sí mismo. En otras palabras, una componente conexa es un conjunto de vértices conectados entre sí, y no está conectado con ningún otro vértice fuera de esa componente. 

Para determinar si un grafo es conexo, se puede realizar un recorrido en profundidad (DFS) o un recorrido en amplitud (BFS) desde un vértice arbitrario y verificar si todos los vértices del grafo se visitan durante el recorrido. Si todos los vértices son visitados,entonces el grafo es conexo; de lo contrario, si quedan vértices sin visitar, el grafo no   es conexo.

18. **¿Cuál es el algoritmo de Dijkstra y cómo se utiliza para encontrar el camino más corto en un grafo ponderado?** 

El algoritmo de Dijkstra se utiliza para encontrar el camino más corto desde un vértice origen a todos los demás vértices en un grafo ponderado con pesos no negativos. Este algoritmo mantiene una lista de distancias mínimas conocidas desde el vértice origen a todos los demás vértices y se va expandiendo desde el vértice origen hacia los vértices adyacentes con la menor distancia acumulada. 

Pasos para el algoritmo de Dijkstra:

1) Inicializar todas las distancias desde el vértice origen a infinito y la distancia al propio vértice origen a cero.
1) Mientras haya vértices sin visitar, seleccionar el vértice con la distancia mínima y marcarlo como visitado.
1) Para el vértice seleccionado, actualizar las distancias a los vértices adyacentes si se encuentra un camino más corto.
1) Repetir los pasos b) y c) hasta que todos los vértices estén marcados como visitados.

Al final del algoritmo, se obtendrán las distancias mínimas desde el vértice origen a todos los demás vértices, lo que representa el camino más corto en el grafo **ponderado.** 

19. **¿Qué es el algoritmo de Kruskal y cuál es su utilidad en el contexto de los árboles de expansión mínima?** 

El algoritmo de Kruskal se utiliza para encontrar el árbol de expansión mínima en un  grafo ponderado, que es un subconjunto de aristas que conecta todos los vértices sin  formar ciclos y cuya suma de pesos es mínima. 

Pasos para el algoritmo de Kruskal:

1) Ordenar todas las aristas del grafo en orden no decreciente según sus pesos.
1) Inicializar un árbol vacío como el árbol de expansión mínima.
1) Recorrer las aristas ordenadas y agregar cada arista al árbol de expansión mínima si no forma un ciclo con las aristas previamente agregadas.

Al finalizar el algoritmo, el árbol resultante será el árbol de expansión mínima con la suma mínima de pesos posibles que conecta todos los vértices del grafo.

20. **¿Qué es la teoría de redes y cuál es su relación con los grafos en aplicaciones del mundo real?** 

La teoría de redes es una rama de las matemáticas y la informática que se enfoca en el estudio de las redes, que pueden ser representadas como grafos. Las redes están presentes en numerosas aplicaciones del mundo real, y la teoría de redes proporciona herramientas y algoritmos para analizar, optimizar y resolver problemas en estas aplicaciones. 

Ejemplos de aplicaciones de la teoría de redes en el mundo real incluyen: 

- **Redes de transporte:** Planificación de rutas óptimas, programación de vuelos y trenes, optimización de flujos de tráfico, etc. 
- **Redes de comunicación:** Optimización de rutas en redes de datos, flujo máximo de información, enrutamiento en Internet, etc. 
- **Redes logísticas:** Optimización de cadenas de suministro, distribución de productos, programación de envíos, etc. 
- **Redes sociales:** Análisis de conexiones sociales, identificación de comunidades, influencia en redes sociales, etc. 
- **Redes eléctricas y de energía:** Optimización del flujo de energía, planificación de redes eléctricas, distribución de energía, etc. 

En todas estas aplicaciones, la teoría de redes utiliza conceptos y algoritmos de grafos para resolver problemas complejos y tomar decisiones eficientes en sistemas interconectados. 
