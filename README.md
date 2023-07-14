<h1 align="center">Udon: Efficient Debugging of User-Defined Functions in Big Data Systems with Line-by-Line Control</h1>

<img src="core/new-gui/src/assets/logos/full_logo_small.png" alt="texera-logo" width="100px" height="60px"/> Udon builts on Texera, a collaborative data analytics workflow system.


## Udon instruction

* Please follow [Guide to Develop Texera](https://github.com/Texera/texera/wiki/Guide-for-Developers) to install Texera, enabling Python UDF.


## Udon components
Udon executes Python UDFs with a two-thread model.
- [Data processing thread](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/runnables/data_processor.py)
- [Control processing thread](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/runnables/main_loop.py)

Udon manages the integrated debugger with a [DebuggerManager](https://github.com/Texera/Udon/blob/master/core/amber/src/main/python/core/architecture/managers/debug_manager.py)


## Acknowledgements

This project is supported by the <a href="http://www.nsf.gov">National Science Foundation</a> under the awards [III 1745673](https://www.nsf.gov/awardsearch/showAward?AWD_ID=1745673), [III 2107150](https://www.nsf.gov/awardsearch/showAward?AWD_ID=2107150), AWS Research Credits, and Google Cloud Platform Education Programs.
