#include <jni.h>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <cmath>
#include <iostream>
#include <assert.h>
#include <algorithm>
#include <android/log.h>

#define ALPHA_0 0

using namespace std;

vector<vector<pair<int, int> > > contours(int arr[], int width, int height);

jobjectArray convertToObjectArray(JNIEnv *env, vector<vector<pair<int, int> > > contours);

vector<pair<int, int> > getFinalPathPointsFromContour(vector<pair<int, int> > points);

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_sdsmdg_harjot_longshadows_shadowutils_LongShadowsGenerator_getContours(
        JNIEnv *env,
        jobject,
        jintArray arr,
        jint width,
        jint height) {

    vector<vector<pair<int, int> > > ans;
    ans.clear();

    jint *c_array = (env)->GetIntArrayElements(arr, NULL);

    __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 1 ");

    ans = contours(c_array, width, height);

    __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 2 ");

//    for (int i = 0; i < ans.size(); i++) {
//        for (int j = 0; j < ans[i].size(); j++) {
//            __android_log_print(ANDROID_LOG_DEBUG, "DEBUG_CPP", " x : %d, y : %d",
//                                ans[i][j].first,
//                                ans[i][j].second);
//        }
//        __android_log_print(ANDROID_LOG_DEBUG, "DEBUG_CPP", " $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ");
//    }

    __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 3 ");

    for (int i = 0; i < ans.size(); i++) {

        __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 3.%d.%d ", i + 1, 0);

        ans[i] = getFinalPathPointsFromContour(ans[i]);

        __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 3.%d.%d ", i + 1, 1);
    }

    __android_log_print(ANDROID_LOG_DEBUG, "TIME_CPP", " 4 ");

    return convertToObjectArray(env, ans);

}

vector<vector<pair<int, int> > > contours(int arr[], int width, int height) {
    int mat[height + 1][width + 1];
    int id[height + 1][width + 1];
    queue<pair<int, int> > q;

    vector<vector<pair<int, int> > > ans;
    ans.clear();

    vector<pair<int, int> > temp;

    int n = width * height;

    for (int i = 0; i < n; i++) {
        mat[i / width][i % width] = arr[i];
        id[i / width][i % width] = 0;
    }

    int cnt = 0, x, y;
    pair<int, int> tp;

    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (id[i][j] == 0 && mat[i][j] != ALPHA_0) {
                cnt++;
                temp.clear();

                while (!q.empty())
                    q.pop();

                q.push(make_pair(i, j));

                while (!q.empty()) {
                    tp = q.front();
                    q.pop();
                    x = tp.first;
                    y = tp.second;

                    if (x < 0 || y < 0 || x >= height || y >= width)
                        continue;

                    if (mat[x][y] == ALPHA_0)
                        continue;

                    if (id[x][y] != 0) {
                        assert(id[x][y] == cnt);
                        continue;
                    }

                    assert(id[x][y] == 0);
                    assert(mat[x][y] != ALPHA_0);

                    id[x][y] = cnt;
                    temp.push_back(make_pair(y, x));

                    q.push(make_pair(x - 1, y - 1));
                    q.push(make_pair(x - 1, y));
                    q.push(make_pair(x - 1, y + 1));
                    q.push(make_pair(x, y - 1));
                    q.push(make_pair(x, y + 1));
                    q.push(make_pair(x + 1, y - 1));
                    q.push(make_pair(x + 1, y));
                    q.push(make_pair(x + 1, y + 1));
                }

                ans.push_back(temp);
            }
        }
    }

    return ans;
}

jobjectArray convertToObjectArray(JNIEnv *env, vector<vector<pair<int, int> > > contours) {
    int size = 0;

    for (int i = 0; i < contours.size(); i++) {
        size += contours[i].size();
    }

    size += contours.size();

    jclass javaClass = (env)->FindClass("com/sdsmdg/harjot/longshadows/models/Point2D");
    jobjectArray objArray = (env)->NewObjectArray(size, javaClass, NULL);
    jmethodID methodID = (env)->GetMethodID(javaClass, "<init>", "(II)V");
    jobject obj;

    int number = 0;
    for (int i = 0; i < contours.size(); i++) {
        vector<pair<int, int> > points = contours[i];
        for (int j = 0; j < points.size(); j++) {
            obj = (env)->NewObject(javaClass, methodID, points[j].first, points[j].second);
            (env)->SetObjectArrayElement(objArray, number, obj);
            (env)->DeleteLocalRef(obj);
            number++;
        }
        obj = (env)->NewObject(javaClass, methodID, -1, -1);
        (env)->SetObjectArrayElement(objArray, number, obj);
        (env)->DeleteLocalRef(obj);
        number++;
    }

    return objArray;
}

vector<pair<int, int> >
getPath(pair<int, int> src, pair<int, int> dest, set<pair<int, int> > Graph) {
    map<pair<int, int>, bool> visited;
    map<pair<int, int>, pair<int, int> > parent;
    visited.clear();
    parent.clear();

    queue<pair<int, int> > Q;
    while (!Q.empty())
        Q.pop();

    Q.push(src);

    visited[src] = true;
    parent[src] = src;

    pair<int, int> tp;
    pair<int, int> pt;
    int x, y;

    while (!Q.empty()) {
        tp = Q.front();
        Q.pop();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                x = tp.first + i;
                y = tp.second + j;
                pt = make_pair(x, y);
                if (Graph.find(pt) != Graph.end() && !visited[pt]) {
                    visited[pt] = true;
                    parent[pt] = tp;
                    Q.push(pt);
                }
            }
        }
    }

    vector<pair<int, int> > path;
    path.clear();

    if (visited[dest] == false) {
        assert(false);
        return path;
    }

    pair<int, int> now = dest;
    while (now != parent[now]) {
        path.push_back(now);
        now = parent[now];
    }
    path.push_back(now);

    return path;
}

long double crossProduct(pair<int, int> p1, pair<int, int> p2, pair<int, int> p3) {
    long double x1, y1, x2, y2;

    x1 = p2.first - p1.first;
    y1 = p2.second - p1.second;

    x2 = p3.first - p1.first;
    y2 = p3.second - p1.second;

    long double ans = x1 * y2 - x2 * y1;

    return ans;
}

long double getArea(vector<pair<int, int> > path, pair<int, int> ref) {
    vector<pair<long double, pair<int, int> > > polarOrder;
    polarOrder.clear();

    long double angle;
    long double H, B;

    for (int i = 0; i < path.size(); i++) {
        H = path[i].second - ref.second;
        B = path[i].first - ref.first;

        angle = atan(H / B);

        polarOrder.push_back(make_pair(angle, path[i]));
    }

    sort(polarOrder.begin(), polarOrder.end());

    path.clear();
    for (int i = 0; i < polarOrder.size(); i++)
        path.push_back(polarOrder[i].second);

    long double area = 0.0;
    for (int i = 1; i < path.size(); i++)
        area = area + crossProduct(ref, path[i - 1], path[i]);

    area = area / 2.0;
    area = abs(area);

    return area;
}

vector<pair<int, int> > getLargestComponent(set<pair<int, int> > Graph) {
    assert(Graph.size() > 0);

    vector<pair<int, int> > component;
    vector<pair<int, int> > now;
    component.clear();

    map<pair<int, int>, bool> visited;
    visited.clear();

    for (set<pair<int, int> >::iterator it = Graph.begin(); it != Graph.end(); it++) {
        if (visited[*it])
            continue;

        now.clear();
        now.push_back(*it);

        queue<pair<int, int> > Q;
        while (!Q.empty())
            Q.pop();

        Q.push(*it);

        visited[*it] = true;

        pair<int, int> tp;
        pair<int, int> pt;
        int x, y;

        while (!Q.empty()) {
            tp = Q.front();
            Q.pop();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    x = tp.first + i;
                    y = tp.second + j;
                    pt = make_pair(x, y);
                    if (Graph.find(pt) != Graph.end() && !visited[pt]) {
                        visited[pt] = true;
                        Q.push(pt);
                        now.push_back(pt);
                    }
                }
            }
        }
        if (component.size() < now.size()) {
            component.clear();
            for (int i = 0; i < now.size(); i++)
                component.push_back(now[i]);
        }
    }

    return component;
}

vector<pair<int, int> > getOuterBoundary(pair<int, int> src, set<pair<int, int> > Graph) {
    map<pair<int, int>, bool> visited;
    visited.clear();

    vector<pair<int, int> > outerBoundary;
    outerBoundary.clear();

    queue<pair<int, int> > Q;
    while (!Q.empty())
        Q.pop();

    Q.push(src);
    visited[src] = true;

    outerBoundary.push_back(src);

    pair<int, int> tp;
    pair<int, int> pt;
    int x, y;

    while (!Q.empty()) {
        tp = Q.front();
        Q.pop();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                x = tp.first + i;
                y = tp.second + j;
                pt = make_pair(x, y);
                if (Graph.find(pt) != Graph.end() && !visited[pt]) {
                    visited[pt] = true;
                    Q.push(pt);
                    outerBoundary.push_back(pt);
                }
            }
        }
    }

    return outerBoundary;
}

vector<pair<int, int> > boundaryPath(vector<pair<int, int> > pts, pair<int, int> ref) {
    set<pair<int, int> > boundary;
    set<pair<int, int> > notboundary;
    boundary.clear();
    notboundary.clear();

    for (int i = 0; i < pts.size(); i++)
        boundary.insert(pts[i]);

    int x1, y1, x2, y2;
    for (int i = 0; i < pts.size(); i++) {
        x1 = pts[i].first;
        y1 = pts[i].second;

        bool flag = false;
        for (int j = -1; j < 2; j++) {
            for (int k = -1; k < 2; k++) {
                x2 = x1 + j;
                y2 = y1 + k;

                if (boundary.find(make_pair(x2, y2)) == boundary.end())
                    flag = true;
            }
        }

        if (!flag)
            notboundary.insert(make_pair(x1, y1));
    }

    for (set<pair<int, int> >::iterator it = notboundary.begin(); it != notboundary.end(); it++)
        boundary.erase(*it);

    vector<pair<long double, pair<int, int> > > polarOrder;
    polarOrder.clear();

    long double angle;
    long double H, B;

    for (set<pair<int, int> >::iterator it = boundary.begin(); it != boundary.end(); it++) {
        H = (it->second) - ref.second;
        B = (it->first) - ref.first;
        angle = atan(H / B);
        polarOrder.push_back(make_pair(angle, *it));
    }

    sort(polarOrder.begin(), polarOrder.end());

    pair<int, int> tangent1, tangent2;
    tangent1 = polarOrder[0].second;
    tangent2 = polarOrder[polarOrder.size() - 1].second;

    vector<pair<int, int> > outerBoundary = getOuterBoundary(tangent1, boundary);

    boundary.clear();
    for (int i = 0; i < outerBoundary.size(); i++)
        boundary.insert(outerBoundary[i]);

    vector<pair<int, int> > path1 = getPath(tangent1, tangent2, boundary);

    vector<pair<int, int> > path2;
    path2.clear();

    for (int i = 0; i < path1.size(); i++)
        boundary.erase(path1[i]);

    for (set<pair<int, int> >::iterator it = boundary.begin(); it != boundary.end(); it++)
        path2.push_back(*it);

    long double area1 = getArea(path1, ref);
    long double area2 = getArea(path2, ref);

    if (area1 > area2)
        return path1;

    int cnt1 = path1.size();

    vector<pair<int, int> > component = getLargestComponent(boundary);

    int cnt2 = component.size();

    long double ratio = ((long double) cnt2) / ((long double) cnt1);

    if (ratio < 0.001)
        return path1;

    boundary.clear();
    for (int i = 0; i < component.size(); i++)
        boundary.insert(component[i]);

    polarOrder.clear();
    for (set<pair<int, int> >::iterator it = boundary.begin(); it != boundary.end(); it++) {
        H = (it->second) - ref.second;
        B = (it->first) - ref.first;
        angle = atan(H / B);
        polarOrder.push_back(make_pair(angle, *it));
    }

    sort(polarOrder.begin(), polarOrder.end());

    tangent1 = polarOrder[0].second;
    tangent2 = polarOrder[polarOrder.size() - 1].second;

    path2 = getPath(tangent1, tangent2, boundary);

    return path2;
}

vector<pair<int, int> > getFinalPathPointsFromContour(vector<pair<int, int> > points) {

    pair<int, int> ref = make_pair(-1000, -1000);

    vector<pair<int, int> > boundary = boundaryPath(points, ref);

    vector<pair<int, int> > boundaryTranslated = boundary;

    for (int i = 0; i < boundaryTranslated.size(); i++) {
        boundaryTranslated[i].first += 30;
        boundaryTranslated[i].second += 30;
    }

    vector<pair<int, int> > pathPoints;

    for (int i = 0; i < boundary.size(); i++) {
        pathPoints.push_back(boundary[i]);
    }

    for (int i = 0; i < boundaryTranslated.size(); i++) {
        pathPoints.push_back(boundaryTranslated[i]);
    }

//    for (int i = boundaryTranslated.size() - 1; i >= 0; i--) {
//        pathPoints.push_back(boundaryTranslated[i]);
//    }

    return pathPoints;

}

