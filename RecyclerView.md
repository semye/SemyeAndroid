# RecyclerViewSample

版本 androidx 1.1.0

onLayout

dispatchLayoutStep1

dispatchLayoutStep2

dispatchLayoutStep3

## RecyclerView.ViewHolder

## RecyclerView.Adapter

- 局部刷新


## Recycler

## RecycledViewPool
RecycledViewPool 可以在多个RecyclerView之间共享View


## ScrapData 废弃的数据

## ViewCacheExtension

Scrap View

A child view that has entered into a temporarily detached state during layout.
Scrap views may be reused without becoming fully detached from the parent RecyclerView,
either unmodified if no rebinding is required
or modified by the adapter if the view was considered dirty.

在布局期间已进入临时分离状态的子视图。
废料视图可以重新使用而不会与父级RecyclerView完全脱离，
如果不需要重新绑定则可以不修改，
而如果视图被认为是脏的，则可以由适配器修改。



Dirty View

A child view that must be rebound by the adapter before
being displayed

一个子视图,在显示之前必须由适配器 bound


